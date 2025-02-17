package com.chrono.service.releasetime;

import com.chrono.domain.releasetime.ReleaseTime;
import com.chrono.domain.user.User;
import com.chrono.repository.ActivityRepository;
import com.chrono.repository.UserRepository;
import com.chrono.request.releasetime.ReleaseTimePutRequest;
import com.chrono.response.activity.ActivityGetResponse;

import lombok.RequiredArgsConstructor;

import com.chrono.exceptions.ResourceNotFoundException;
import com.chrono.exceptions.InvalidReleaseTimeException;

import org.springframework.stereotype.Component;

// Classe auxiliar para manipulação dos dados relacionados ao lancamento de hora (ReleaseTime).
@Component
@RequiredArgsConstructor
public class ReleaseTimeHelper {

    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;

    /**
     * Atualiza os campos do objeto ReleaseTime com os dados fornecidos no DTO.
     * 
     * @param releaseTime o objeto ReleaseTime a ser atualizado.
     * @param dto os dados de atualização do ReleaseTime.
     */
    public void updateReleaseTimeFields(ReleaseTime releaseTime, ReleaseTimePutRequest dto) {
        releaseTime.setDescription(dto.description());
        releaseTime.setStartDate(dto.startDate());
        releaseTime.setEndDate(dto.endDate());
        updateReleaseTimeAssociations(releaseTime, dto.activity(), dto.user());
    }

    /**
     * Atualiza as associações de ReleaseTime com a Activity e o User.
     * 
     * @param releaseTime o objeto ReleaseTime cujas associações serão atualizadas.
     * @param activityDto os dados da atividade (Activity) a serem associados.
     * @param userDto os dados do usuário (User) a serem associados.
     * @throws ResourceNotFoundException se a atividade ou o usuário não forem encontrados.
     */
    public void updateReleaseTimeAssociations(ReleaseTime releaseTime, ActivityGetResponse activityDto, User userDto) {
        if (activityDto != null) {
            releaseTime.setActivity(activityRepository.findById(activityDto.id())
                    .orElseThrow(() -> new ResourceNotFoundException("Activity not found")));
        }
        if (userDto != null) {
            releaseTime.setUser(userRepository.findById(userDto.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found")));
        }
    }

    /**
     * Valida se as datas do ReleaseTime estão corretas.
     * A data de término não pode ser anterior à data de início.
     * 
     * @param releaseTime o objeto ReleaseTime cujas datas serão validadas.
     * @throws InvalidReleaseTimeException se a data de término for anterior à data de início.
     */
    public void validateReleaseTimeDates(ReleaseTime releaseTime) {
        if (releaseTime.getEndDate() != null && releaseTime.getStartDate() != null &&
                releaseTime.getEndDate().isBefore(releaseTime.getStartDate())) {
            throw new InvalidReleaseTimeException("End date cannot be before start date.");
        }
    }
}

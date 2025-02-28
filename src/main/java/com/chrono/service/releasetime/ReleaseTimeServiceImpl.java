package com.chrono.service.releasetime;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.chrono.domain.releasetime.ReleaseTime;
import com.chrono.exceptions.ResourceNotFoundException;
import com.chrono.mapper.ReleaseTimeMapper;
import com.chrono.repository.ReleaseTimeRepository;
import com.chrono.request.releasetime.ReleaseTimePostRequest;
import com.chrono.request.releasetime.ReleaseTimePutRequest;
import com.chrono.response.releasetime.ReleaseTimeGetResponse;
import com.chrono.response.releasetime.ReleaseTimePostResponse;
import com.chrono.response.releasetime.ReleaseTimePutResponse;

import lombok.RequiredArgsConstructor;

// Implementação do serviço de lancamentos de horas.
@Service
@RequiredArgsConstructor
public class ReleaseTimeServiceImpl implements ReleaseTimeService {

    private final ReleaseTimeRepository releaseTimeRepository;
    private final ReleaseTimeMapper mapper;
    private final ReleaseTimeHelper releaseTimeHelper;

    /**
     * Obtém todos os lancamentos de horas.
     * 
     * @return uma lista de respostas de lancamentos de horas.
     */
    @Override
    public List<ReleaseTimeGetResponse> findAllReleases() {
        return mapper.toReleaseTimeGetResponseList(releaseTimeRepository.findAll());
    }

    /**
     * Obtém um lancamentos de horapelo ID.
     * 
     * @param id o ID do lancamento de hora a ser buscado.
     * @return a resposta do lancamento de hora encontrado.
     * @throws ResourceNotFoundException se o lancamento de hora não for encontrado.
     */
    @Override
    public ReleaseTimeGetResponse findReleaseTimeById(Integer id) {
        ReleaseTime releaseTime = releaseTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Release time not found"));
        return mapper.toReleaseTimeGetResponse(releaseTime);
    }

    /**
     * Atualiza um lancamento de hora existente.
     * 
     * @param dto os dados de atualização do lancamento de hora.
     * @param id o ID do lancamento de hora a ser atualizado.
     * @return a resposta do lancamento de hora atualizado.
     * @throws ResourceNotFoundException se o lancamento de hora não for encontrado.
     */
    @Override
    public ReleaseTimePutResponse updateReleaseTime(ReleaseTimePutRequest dto, Integer id) {
        ReleaseTime currentReleaseTime = releaseTimeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Release time not found"));

        releaseTimeHelper.updateReleaseTimeFields(currentReleaseTime, dto);
        releaseTimeRepository.save(currentReleaseTime);
        return mapper.toReleaseTimePutResponse(currentReleaseTime);
    }

    /**
     * Salva um novo lancamento de hora.
     * 
     * @param postRequest os dados do novo lancamento de hora.
     * @return a resposta do lancamento de hora salvo.
     */
    @Override
    public ReleaseTimePostResponse saveReleaseTime(ReleaseTimePostRequest postRequest) {
        ReleaseTime releaseTime = mapper.toReleaseTimePost(postRequest);
        releaseTimeHelper.validateReleaseTimeDates(releaseTime);
        releaseTimeHelper.updateReleaseTimeAssociations(releaseTime, postRequest.activity(), postRequest.user());
        releaseTime.setId(null);
        return mapper.toReleaseTimePostResponse(releaseTimeRepository.save(releaseTime));
    }

    /**
     * Exclui um lancamento de hora pelo ID.
     * 
     * @param id o ID do lancamento de hora a ser excluído.
     * @throws ResourceNotFoundException se o lancamento de hora não for encontrado.
     */
    @Override
    public void deleteReleaseTimeById(Long id) {
        if (!releaseTimeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Release Time not found");
        }
        releaseTimeRepository.deleteById(id);
    }

    /**
     * Recupera uma lista de lançamentos de horas associadas a um ID de usuário específico.
     *
     * @param userId o ID do usuário cujos lançamentos de horas devem ser recuperados
     * @return uma lista de objetos {@link ReleaseTimeGetResponse} representando os lançamentos de horas do usuário especificado
     * @throws ResourceNotFoundException se nenhum lançamento de horas for encontrado para o ID de usuário fornecido
     */
    public List<ReleaseTimeGetResponse> getReleaseTimesByUserId(Integer userId) {
        List<ReleaseTime> releaseTimes = releaseTimeRepository.findByUserId(userId);
        if (releaseTimes.isEmpty()) {
            throw new ResourceNotFoundException("No release times found for the user ID: " + userId);
        }

        return releaseTimes.stream()
                .map(mapper::toReleaseTimeGetResponse)
                .collect(Collectors.toList());
    }
}

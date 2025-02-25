package com.chrono.service.activity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.activity.Activity;
import com.chrono.exceptions.ResourceNotFoundException;
import com.chrono.mapper.ActivityMapper;
import com.chrono.repository.ActivityRepository;
import com.chrono.request.activity.ActivityPostRequest;
import com.chrono.request.activity.ActivityPutRequest;
import com.chrono.response.activity.ActivityGetResponse;
import com.chrono.response.activity.ActivityPostResponse;
import com.chrono.response.activity.ActivityPutResponse;

import lombok.RequiredArgsConstructor;

/**
 * Implementação do serviço de atividades.
 */
@Service
@RequiredArgsConstructor
public class ActivityServiceImpl implements ActivityService {

    private final ActivityRepository activityRepository;
    private final ActivityMapper mapper;
    private final ActivityHelper activityHelper;

    /**
     * Obtém todas as atividades.
     *
     * @return Lista de respostas de atividades.
     * @throws ResourceNotFoundException se nenhuma atividade for encontrada.
     */
    @Override
    public List<ActivityGetResponse> findAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        if (activities.isEmpty()) {
            throw new ResourceNotFoundException("No activities found");
        }
        return mapper.toActivityGetResponseList(activities);
    }

    /**
     * Obtém atividades pelo nome.
     *
     * @param name Nome da atividade.
     * @return Lista de respostas de atividades.
     * @throws ResourceNotFoundException se nenhuma atividade for encontrada.
     */
    @Override
    public List<ActivityGetResponse> findActivityByName(String name) {
        List<Activity> activities = activityRepository.findByNameContaining(name);
        if (activities.isEmpty()) {
            throw new ResourceNotFoundException("Activity not found");
        }
        return mapper.toActivityGetResponseList(activities);
    }

    /**
     * Obtém atividade pelo ID.
     *
     * @param id ID da atividade.
     * @return Resposta da atividade.
     */
    @Override
    public ActivityGetResponse findActivityById(Integer id) {
        Activity activity = activityHelper.getActivityById(id, activityRepository);
        return mapper.toActivityGetResponse(activity);
    }

    /**
     * Atualiza uma atividade pelo ID.
     *
     * @param id  ID da atividade.
     * @param dto Dados para atualização da atividade.
     * @return Resposta da atividade atualizada.
     */
    @Override
    public ActivityPutResponse updateActivity(Integer id, ActivityPutRequest dto) {
        Activity activity = activityHelper.getActivityById(id, activityRepository);
        activityHelper.updateActivityFields(activity, dto);
        activityRepository.save(activity);
        return mapper.toActivityPutResponse(activity);
    }

    /**
     * Salva uma nova atividade.
     *
     * @param request Dados da nova atividade.
     * @return Resposta da atividade salva.
     */
    @Override
    public ActivityPostResponse saveActivity(ActivityPostRequest request) {
        Activity activity = new Activity();
        activityHelper.updateActivityFields(activity, request);
        activity.setProject(activityHelper.getProjectById(request.project().id()));
        activity.setResponsible(activityHelper.getUserById(request.responsible().id()));
        activityRepository.save(activity);
        return mapper.toActivityPostResponse(activity);
    }

    /**
     * Deleta uma atividade pelo ID.
     *
     * @param id ID da atividade.
     * @throws ResourceNotFoundException se a atividade não for encontrada.
     */
    @Override
    public void deleteActivityById(Long id) {
        if (!activityRepository.existsById(id)) {
            throw new ResourceNotFoundException("Activity not found");
        }
        activityRepository.deleteById(id);
    }

    /**
     * Lista atividades pelo ID do projeto.
     *
     * @param projectId ID do projeto.
     * @return Lista de respostas de atividades.
     * @throws ResourceNotFoundException se nenhuma atividade for encontrada.
     */
    public List<ActivityGetResponse> findActivitiesByProjectId(Integer projectId) {
        List<Activity> activities = activityRepository.findByProjectId(projectId);
        if (activities.isEmpty()) {
            throw new ResourceNotFoundException("No activities found for the given project ID");
        }
        return mapper.toActivityGetResponseList(activities);
    }
}

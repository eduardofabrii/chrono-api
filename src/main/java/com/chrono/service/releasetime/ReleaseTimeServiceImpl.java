package com.chrono.service.releasetime;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.releasetime.ReleaseTime;
import com.chrono.domain.user.User;
import com.chrono.mapper.ReleaseTimeMapper;
import com.chrono.repository.ActivityRepository;
import com.chrono.repository.ReleaseTimeRepository;
import com.chrono.repository.UserRepository;
import com.chrono.request.releasetime.ReleaseTimePostRequest;
import com.chrono.request.releasetime.ReleaseTimePutRequest;
import com.chrono.response.activity.ActivityGetResponse;
import com.chrono.response.releasetime.ReleaseTimeGetResponse;
import com.chrono.response.releasetime.ReleaseTimePostResponse;
import com.chrono.response.releasetime.ReleaseTimePutResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReleaseTimeServiceImpl implements ReleaseTimeService {

    private final ReleaseTimeRepository releaseTimeRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final ReleaseTimeMapper mapper;

    // GET all release time
    @Override
    public List<ReleaseTimeGetResponse> findAllReleases() {
        return mapper.toReleaseTimeGetResponseList(releaseTimeRepository.findAll());
    }

    
    // GET release time by id
    @Override
    public ReleaseTimeGetResponse findReleaseTimeById(Integer id) {
        return mapper.toReleaseTimeGetResponse(releaseTimeRepository.findById(id).orElse(null));
    }

    // PUT release time by id
    @Override
    public ReleaseTimePutResponse updateReleaseTime(ReleaseTimePutRequest dto, Integer id) {
        ReleaseTime currentReleaseTime = releaseTimeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Release time not found"));

        updateReleaseTimeFields(currentReleaseTime, dto);
        releaseTimeRepository.save(currentReleaseTime);
        return mapper.toReleaseTimePutResponse(currentReleaseTime);
    }

    // POST release time
    @Override
    public ReleaseTimePostResponse saveReleaseTime(ReleaseTimePostRequest postRequest) {
        ReleaseTime releaseTime = mapper.toReleaseTimePost(postRequest);
        validateReleaseTimeDates(releaseTime);
        updateReleaseTimeAssociations(releaseTime, postRequest.activity(), postRequest.user());
        releaseTime.setId(null);
        return mapper.toReleaseTimePostResponse(releaseTimeRepository.save(releaseTime));
    }

    // DELETE release time by id
    @Override
    public void deleteReleaseTimeById(Long id) {
        releaseTimeRepository.deleteById(id);
    }

    // Functions to fetch and set all fields on body request
    private void updateReleaseTimeFields(ReleaseTime releaseTime, ReleaseTimePutRequest dto) {
        releaseTime.setDescription(dto.description());
        releaseTime.setStartDate(dto.startDate());
        releaseTime.setEndDate(dto.endDate());
        updateReleaseTimeAssociations(releaseTime, dto.activity(), dto.user());
    }

    private void updateReleaseTimeAssociations(ReleaseTime releaseTime, ActivityGetResponse activityDto, User userDto) {
        if (activityDto != null) {
            releaseTime.setActivity(activityRepository.findById(activityDto.id())
                    .orElseThrow(() -> new RuntimeException("Activity not found")));
        }
        if (userDto != null) {
            releaseTime.setUser(userRepository.findById(userDto.getId())
                    .orElseThrow(() -> new RuntimeException("User not found")));
        }
    }

    private void validateReleaseTimeDates(ReleaseTime releaseTime) {
        if (releaseTime.getEndDate() != null && releaseTime.getStartDate() != null &&
                releaseTime.getEndDate().isBefore(releaseTime.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
    }
}

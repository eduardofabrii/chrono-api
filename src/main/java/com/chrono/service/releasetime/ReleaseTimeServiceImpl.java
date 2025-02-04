package com.chrono.service.releasetime;

import java.util.List;

import org.springframework.stereotype.Service;

import com.chrono.domain.releasetime.ReleaseTime;
import com.chrono.mapper.ReleaseTimeMapper;
import com.chrono.repository.ReleaseTimeRepository;
import com.chrono.request.releasetime.ReleaseTimePostRequest;
import com.chrono.request.releasetime.ReleaseTimePutRequest;
import com.chrono.response.releasetime.ReleaseTimeGetResponse;
import com.chrono.response.releasetime.ReleaseTimePostResponse;
import com.chrono.response.releasetime.ReleaseTimePutResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReleaseTimeServiceImpl implements ReleaseTimeService {

    private final ReleaseTimeRepository releaseTimeRepository;
    private final ReleaseTimeMapper mapper;

    // GET all release times
    @Override
    public List<ReleaseTimeGetResponse> findAllReleases() {
        List<ReleaseTime> releases = releaseTimeRepository.findAll();
        return mapper.toReleaseTimeGetResponseList(releases);
    }

    // GET release time by ID
    @Override
    public ReleaseTimeGetResponse findReleaseTimeById(Integer id) {
        ReleaseTime releaseTime = releaseTimeRepository.findById(id).orElse(null);
        return mapper.toReleaseTimeGetResponse(releaseTime);
    }

    // PUT to update release time
    @Override
    public ReleaseTimePutResponse updateReleaseTime(ReleaseTimePutRequest dto, Integer id) {
        ReleaseTime releaseTime = mapper.toReleaseTimePut(dto);
        releaseTime.setId(id);

        ReleaseTime currentReleaseTime = releaseTimeRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Release time not found"));
        currentReleaseTime.setDescription(releaseTime.getDescription());
        currentReleaseTime.setStartDate(releaseTime.getStartDate());
        currentReleaseTime.setEndDate(releaseTime.getEndDate());

        releaseTimeRepository.save(currentReleaseTime);
        return mapper.toReleaseTimePutResponse(currentReleaseTime);
    }

    // POST to save release time
    @Override
    public ReleaseTimePostResponse saveReleaseTime(ReleaseTimePostRequest postRequest) {
        ReleaseTime releaseTime = mapper.toReleaseTimePost(postRequest);
        
        // Validate date logic
        if (releaseTime.getEndDate() != null && releaseTime.getStartDate() != null && releaseTime.getEndDate().isBefore(releaseTime.getStartDate())) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }

        releaseTime.setId(null); // Ensure new record (not update)
        releaseTime = releaseTimeRepository.save(releaseTime);
        
        return mapper.toReleaseTimePostResponse(releaseTime);
    }

    // DELETE to delete release time
    @Override
    public void deleteReleaseTimeById(Long id) {
        releaseTimeRepository.deleteById(id);
    }
}

package com.chrono.service.releasetime;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.chrono.domain.releasetime.ReleaseTime;
import com.chrono.repository.ReleaseTimeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReleaseTimeServiceImpl implements ReleaseTimeService {

    private final ReleaseTimeRepository releaseTimeRepository;

    @Override
    public List<ReleaseTime> findAllReleases() {
        return releaseTimeRepository.findAll();
    }

    // GET to find release time by id
    @Override
    public ReleaseTime findReleaseTimeById(Integer id) {
        Optional<ReleaseTime> releaseTime = releaseTimeRepository.findById(id);
        return releaseTime.orElse(null);
    }

    // PUT to update release time
    @Override
    public void updateReleaseTime(ReleaseTime releaseTime) {
        ReleaseTime currentProject = this.findReleaseTimeById(releaseTime.getId());
        currentProject.setDescription(releaseTime.getDescription());
        currentProject.setStartDate(releaseTime.getStartDate());
        currentProject.setEndDate(releaseTime.getEndDate());

        releaseTimeRepository.save(currentProject);
    }

    // POST to save release time
    @Override
    public ReleaseTime saveReleaseTime(ReleaseTime releaseTime) {
        // Date validator
        if (releaseTime.getEndDate() != null && releaseTime.getStartDate() != null && releaseTime.getEndDate().isBefore(releaseTime.getStartDate())) {
            throw new IllegalArgumentException("A data de fim não pode ser anterior à data de início.");
        }

        releaseTime.setId(null);
        return releaseTimeRepository.save(releaseTime);
    }

    // DELETE to delete release time
    @Override
    public void deleteReleaseTimeById(Long id) {
        releaseTimeRepository.deleteById(id);
    }
}

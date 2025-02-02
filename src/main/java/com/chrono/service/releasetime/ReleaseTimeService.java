package com.chrono.service.releasetime;

import java.util.List;

import com.chrono.domain.releasetime.ReleaseTime;

public interface ReleaseTimeService {
    public List<ReleaseTime> findAllReleases();
    public ReleaseTime findReleaseTimeById(Integer id);
    public void updateReleaseTime(ReleaseTime releaseTime);
    public ReleaseTime saveReleaseTime(ReleaseTime releaseTime);
    public void deleteReleaseTimeById(Long id);
}

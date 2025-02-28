package com.chrono.service.releasetime;

import java.util.List;

import com.chrono.request.releasetime.ReleaseTimePostRequest;
import com.chrono.request.releasetime.ReleaseTimePutRequest;
import com.chrono.response.releasetime.ReleaseTimeGetResponse;
import com.chrono.response.releasetime.ReleaseTimePostResponse;
import com.chrono.response.releasetime.ReleaseTimePutResponse;

public interface ReleaseTimeService {
    public List<ReleaseTimeGetResponse> findAllReleases();
    public ReleaseTimeGetResponse findReleaseTimeById(Integer id);
    public ReleaseTimePutResponse updateReleaseTime(ReleaseTimePutRequest releaseTime, Integer id);
    public ReleaseTimePostResponse saveReleaseTime(ReleaseTimePostRequest releaseTime);
    public void deleteReleaseTimeById(Long id);
    public List<ReleaseTimeGetResponse> getReleaseTimesByUserId(Integer userId);
}

package com.chrono.service.releasetime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chrono.domain.activity.Activity;
import com.chrono.domain.project.Project;
import com.chrono.domain.releasetime.ReleaseTime;
import com.chrono.domain.user.User;
import com.chrono.repository.ReleaseTimeRepository;
import com.chrono.repository.UserRepository;
import com.chrono.service.activity.ActivityService;
import com.chrono.service.project.ProjectService;
import com.chrono.service.user.UserService;

@Service
public class ReleaseTimeServiceImpl implements ReleaseTimeService {

    @Autowired
    private ReleaseTimeRepository releaseTimeRepository;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

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
        checkReleaseTime(releaseTime);
        
        ReleaseTime currentProject = this.findReleaseTimeById(releaseTime.getId());
        currentProject.setDescription(releaseTime.getDescription());
        currentProject.setStartDate(releaseTime.getStartDate());
        currentProject.setEndDate(releaseTime.getEndDate());

        releaseTimeRepository.save(currentProject);
    }

    // POST to save release time
    @Override
    public ReleaseTime saveReleaseTime(ReleaseTime releaseTime) {
        checkReleaseTime(releaseTime);
        releaseTime.setId(null);
        return releaseTimeRepository.save(releaseTime);
    }

    // DELETE to delete release time
    @Override
    public void deleteReleaseTimeById(Long id) {
        releaseTimeRepository.deleteById(id);
    }

    // Method to check entities to a releaseTime
    private void checkReleaseTime(ReleaseTime releaseTime) {
        if (releaseTime.getActivity() != null && releaseTime.getActivity().getId() != null) {
            Activity activity = activityService.findActivityById(releaseTime.getActivity().getId());
            releaseTime.setActivity(activity);
        }

        // Verify and load the project within the activity
        if (releaseTime.getActivity() != null && releaseTime.getActivity().getProject() != null && releaseTime.getActivity().getProject().getId() != null) {
            Project project = projectService.findProjectById(releaseTime.getActivity().getProject().getId());
            releaseTime.getActivity().setProject(project);
        }

        // Verify and load the user
        if (releaseTime.getUser() != null && releaseTime.getUser().getId() != null) {
            User user = userService.findUserById(releaseTime.getUser().getId());
            releaseTime.setUser(user); 
        }
    }
}

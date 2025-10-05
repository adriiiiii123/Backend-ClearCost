package com.galaxiawonder.propgms.propgmsplatform.projects.application.internal.commandservices;

import com.galaxiawonder.propgms.propgmsplatform.projects.domain.model.aggregates.Milestone;
import com.galaxiawonder.propgms.propgmsplatform.projects.domain.model.commands.CreateMilestoneCommand;
import com.galaxiawonder.propgms.propgmsplatform.projects.domain.model.commands.DeleteMilestoneCommand;
import com.galaxiawonder.propgms.propgmsplatform.projects.domain.model.commands.UpdateMilestoneCommand;
import com.galaxiawonder.propgms.propgmsplatform.projects.domain.services.MilestoneCommandService;
import com.galaxiawonder.propgms.propgmsplatform.projects.infrastructure.persistence.jpa.repositories.MilestoneRepository;
import com.galaxiawonder.propgms.propgmsplatform.projects.infrastructure.persistence.jpa.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MilestoneCommandServiceImpl implements MilestoneCommandService {
    private final MilestoneRepository milestoneRepository;
    private final ProjectRepository projectRepository;

    public MilestoneCommandServiceImpl(MilestoneRepository milestoneRepository
    , ProjectRepository projectRepository) {
        this.milestoneRepository = milestoneRepository;
        this.projectRepository = projectRepository;
    }

    @Override
    public Optional<Milestone> handle(CreateMilestoneCommand command) {
        if (command == null){
            throw new IllegalArgumentException("CreateMilestoneCommand cannot be null");
        }

        var project = projectRepository.findById(command.projectId().projectId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "The project with the id " + command.projectId().projectId() + "does not exists"
                ));
        var projectDateRange = project.getDateRange();

        var milestone = new Milestone(command);
        milestone.validateDateRange(projectDateRange);

        var createdMilestone = milestoneRepository.save(milestone);

        return Optional.of(createdMilestone);
    }

    @Override
    public Optional<Milestone> handle(UpdateMilestoneCommand command){
        if (command == null){
            throw new IllegalArgumentException("UpdateMilestoneCommand cannot be null");
        }
        var milestone = milestoneRepository.findById(command.milestoneId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "The milestone with the id " + command.milestoneId() + "does not exists"
                ));

        var milestoneName = command.name();
        if (milestoneName != null){
            milestone.reassignName(milestoneName);
        }

        var milestoneDescription = command.description();
        if (milestoneDescription != null){
            milestone.reassignDescription(milestoneDescription);
        }

        var milestoneDateRange = command.dateRange();
        if (milestoneDateRange != null){
            milestone.validateDateRange(milestoneDateRange);
            milestone.reassignDateRange(milestoneDateRange);
        }

        var updatedMilestone = milestoneRepository.save(milestone);
        return Optional.of(updatedMilestone);
    }

    @Override
    public void handle(DeleteMilestoneCommand command){
        if (command == null){
            throw new IllegalArgumentException("DeleteMilestoneCommand cannot be null");
        }
        var milestone = milestoneRepository.findById(command.milestoneId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "The milestone with the id " + command.milestoneId() + "does not exists"
                ));
        milestoneRepository.delete(milestone);
    }
}

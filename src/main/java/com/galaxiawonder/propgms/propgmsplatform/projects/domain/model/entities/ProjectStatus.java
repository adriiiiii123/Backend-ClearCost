package com.galaxiawonder.propgms.propgmsplatform.projects.domain.model.entities;

import com.galaxiawonder.propgms.propgmsplatform.projects.domain.model.aggregates.Project;
import com.galaxiawonder.propgms.propgmsplatform.projects.domain.model.valueobjects.ProjectStatuses;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

/**
 * ProjectStatus
 *
 * @summary
 * Entity that represents the status of a project.
 * This class is backed by the {@link ProjectStatuses} enum and defines
 * a fixed set of lifecycle stages used to track project progress.
 *
 * <p>Examples: {@code BASIC_STUDIES}, {@code DESIGN_IN_PROCESS}, {@code APPROVED}</p>
 *
 * @author
 * Galaxia Wonder Development Team
 * @since 1.0
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStatus {

    /**
     * Database identifier for the project status.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Enum description representing the status of the project.
     */
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private ProjectStatuses name;

    /**
     * Constructs a {@code ProjectStatus} with a specified {@link ProjectStatuses} description.
     *
     * @param name the predefined project status
     */
    public ProjectStatus(ProjectStatuses name) {
        this.name = name;
    }

    /**
     * Returns the default project status to be assigned when none is specified.
     *
     * @return a {@code ProjectStatus} instance with {@code BASIC_STUDIES} as default
     */
    public static ProjectStatus getDefaultStatus() {
        return new ProjectStatus(ProjectStatuses.BASIC_STUDIES);
    }

    /**
     * Converts a string representation of the project status projectName to a {@link ProjectStatus} instance.
     *
     * @param name the string description of the enum constant
     * @return a new {@code ProjectStatus} instance with the corresponding enum description
     * @throws IllegalArgumentException if the projectName does not match any enum constant
     */
    public static ProjectStatus toProjectStatusFromName(String name) {
        return new ProjectStatus(ProjectStatuses.valueOf(name));
    }

    /**
     * Validates a list of project statuses. If null or empty, returns a list with the default status.
     *
     * @param statuses the list of project statuses to validate
     * @return the original list or a singleton list with the default status
     */
    public static List<ProjectStatus> validateStatusSet(List<ProjectStatus> statuses) {
        return statuses == null || statuses.isEmpty()
                ? List.of(getDefaultStatus())
                : statuses;
    }

    /**
     * Returns the string representation of the enum-based project status.
     *
     * @return the projectName of the project status as a string
     */
    public String getStringName() {
        return name.name();
    }
}

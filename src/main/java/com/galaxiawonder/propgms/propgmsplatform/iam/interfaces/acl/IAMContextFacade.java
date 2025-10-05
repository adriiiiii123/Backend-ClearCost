package com.galaxiawonder.propgms.propgmsplatform.iam.interfaces.acl;

import com.galaxiawonder.propgms.propgmsplatform.projects.domain.model.entities.Specialty;
import com.galaxiawonder.propgms.propgmsplatform.shared.domain.model.valueobjects.ProfileDetails;

/**
 * IAMContextFacade
 *
 * @summary
 * Facade interface that exposes selected IAM operations to other bounded contexts.
 * This abstraction allows cross-context queries related to identity without exposing
 * the internal structure or rules of the IAM context.
 *
 * <p>It is typically used for read-only access to basic person information.</p>
 *
 * <p>Examples of usage:</p>
 * <ul>
 *   <li>Fetch basic person info by {@code personId}</li>
 *   <li>Find a person using their {@code email}</li>
 * </ul>
 *
 * @author
 * Galaxia Wonder Development Team
 * @since 1.0
 */
public interface IAMContextFacade {

    /**
     * Retrieves basic information of a person using their unique identifier.
     *
     * @param personId the identifier of the person
     * @return a {@link ProfileDetails} object containing projectName and email
     */
    ProfileDetails getProfileDetailsByPersonId(Long personId);

    /**
     * Retrieves basic information of a person using their email address.
     *
     * @param email the email address of the person
     * @return a {@link ProfileDetails} object containing projectName and email
     */
    ProfileDetails getProfileDetailsByEmail(String email);

    /**
     * Retrieves detailed profile information of a person given their internal system ID.
     *
     * <p>This method is commonly used to fetch personal data associated with a person,
     * such as projectName, email, or role, typically for display or reference in other modules
     * (e.g., invitations, dashboards, reports).</p>
     *
     * @param id the internal ID of the person
     * @return an object containing the person's profile details
     *
     * @since 1.0
     */
    ProfileDetails getProfileDetailsById(Long id);

    /**
     * Retrieves the unique identifier of a person based on their email address.
     *
     * <p>This method is typically used when resolving relationships or
     * linking a person to another domain entity (e.g., invitations, memberships).</p>
     *
     * @param email the email address used to look up the person
     * @return the unique identifier of the person as a {@link Long}
     *
     * @since 1.0
     */
    Long getPersonIdFromEmail(String email);

    Specialty getSpecialtyFromPersonId(Long id);
}


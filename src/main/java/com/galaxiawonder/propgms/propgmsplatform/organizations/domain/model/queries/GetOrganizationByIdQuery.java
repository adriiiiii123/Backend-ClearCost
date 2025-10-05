package com.galaxiawonder.propgms.propgmsplatform.organizations.domain.model.queries;

/**
 * @summary
 * This class represents the query to get an organization source by its id.
 * @param id - the id of the organization source.
 */
public record GetOrganizationByIdQuery(Long id) {
    public GetOrganizationByIdQuery {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }
}
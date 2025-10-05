package com.galaxiawonder.propgms.propgmsplatform.change.interfaces.rest.resources;

public record CreateChangeProcessResource(
        Long projectId,
        String justification
) {
    public CreateChangeProcessResource {
        if (justification == null || justification.isBlank()) throw new IllegalArgumentException("justification cannot be null or empty");

    }
}

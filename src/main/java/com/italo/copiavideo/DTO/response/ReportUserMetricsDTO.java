package com.italo.copiavideo.DTO.response;

import java.util.List;

public record ReportUserMetricsDTO(
        List<UserDTO> lastUsers, List<UserDTO> usersWithMostIdeas
) {
}

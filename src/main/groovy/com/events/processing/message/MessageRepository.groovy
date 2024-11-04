package com.events.processing.message

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.stereotype.Repository

@Repository
class MessageRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate

    MessageRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate
    }

    void saveMessage(Message message) {
        String sql = "INSERT INTO messages (content) VALUES (:content)"
        MapSqlParameterSource params = new MapSqlParameterSource()
        params.addValue("content", message.content)

        namedParameterJdbcTemplate.update(sql, params)
    }
}

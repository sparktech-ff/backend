package com.sparktechcode.ff.core.featureflag.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.PGConnection;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Slf4j
@Service
@RequiredArgsConstructor
public class RefreshListenerService {
    private final FeatureFlagService service;
    private final DataSource dataSource;

    @Scheduled(fixedDelay = 1)
    public void start() {
        startListener();
    }

    @SneakyThrows
    private void startListener() {
        log.info("Feature flags listener started.");
        var connection = dataSource.getConnection();
        connection.setAutoCommit(true);
        var pgConnection = connection.unwrap(PGConnection.class);
        try (var st = connection.createStatement()) {
            st.execute("LISTEN refresh_broadcast");
            while (true) {
                handleNotifications(pgConnection);
            }
        }
    }

    @SneakyThrows
    private void handleNotifications(PGConnection pgConnection) {
        var notifications = pgConnection.getNotifications(60_000);
        if (notifications != null) {
            for (var notification : notifications) {
                var channel = notification.getName();
                if ("refresh_broadcast".equals(channel)) {
                    service.refresh();
                    log.info("Feature flags refreshed.");
                }
            }
        }
    }
}

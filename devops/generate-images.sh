#!/usr/bin/env bash
(cd itry-config-server; mvn clean install docker:build)
(cd itry-discovery-server; mvn clean install docker:build)
(cd itry-route-service; mvn clean install docker:build)
(cd itry-calculate-travel-service; mvn clean install docker:build)

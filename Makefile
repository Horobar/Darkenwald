tailwind:
	npx tailwindcss -i ./src/main/resources/input.css -o ./src/main/resources/web/app/output.css --watch

keycloak:
	docker run --name keycloak -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -p 8180:8080 quay.io/keycloak/keycloak:25.0.0 start-dev

remdev-up:
	mvn clean package -Dquarkus.package.type=mutable-jar -DskipTests
	docker compose up

remdev-connect:
	mvn quarkus:remote-dev -Ddebug=false \
  		-Dquarkus.package.jar.type=mutable-jar \
  		-Dquarkus.live-reload.url=http://localhost:8080 \
  		-Dquarkus.live-reload.password=123

free8080:
	pid=$(lsof -i:8080 -t);
	kill -TERM $pid || kill -KILL $pid



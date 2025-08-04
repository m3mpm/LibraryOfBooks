build:
	mvn clean install

docker-build:
	docker-compose build

docker-build-up:
	docker-compose up --build

docker-up:
	docker-compose up

docker-down:
	docker-compose down

docker-clean: docker-down
	docker system prune -af

.PHONY: build docker-build docker-up docker-build-up docker-down docker-clean
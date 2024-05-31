Это приложение является ботом для VKontakte, написанным на Java с использованием Spring Boot.

## Требования

- Java 11 или новее
- Maven
- Ngrok (для проброса локального сервера в интернет)
Перед запуском приложения необходимо отредактировать файл `application.properties` в директории `src/main/resources/` и добавить туда следующие параметры:

vk.confirmation.code=YOUR_CONFIRMATION_CODE
vk.secret=YOUR_SECRET_KEY
vk.access.token=YOUR_ACCESS_TOKEN

## Запуск

- Клонируйте репозиторий
- Соберите проект с помощью Maven (mvn clean install)
- Запустите приложение (mvn spring-boot:run)
- Запустите Ngrok для проброса локального сервера (ngrok http 8080)
- Настройте Callback API в вашей группе VK, используя URL, предоставленный Ngrok. (После URL необходимо дописать /callback, например: https://ngrok-url-example.app/callback)


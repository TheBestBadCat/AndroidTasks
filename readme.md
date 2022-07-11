Учебный проект с использованием MVVM, сlean architecture, retrofit, coroutines, dagger

ТЗ
Реализовать приложение, позволяющее пользователю оформить займ онлайн.
Типичный сценарий: Пользователь регистрируется в приложении, после чего осуществляет вход. Видит информацию, поясняющую что ему делать далее.
Поняв, как все работает, пользователь оформляет заявку на займ, указав необходимые данные. Если заявка оформлена успешно, пользователь видит экран с успехом и пояснение что ему делать дальше. Зайдя в приложение повторно пользователь видит список оформленных займов и их статусы. Он может перейти в каждую конкретную заявку и посмотреть подробную информацию о ней.

Функциональные требования: Неавторизованный пользователь должен иметь возможность:
• Зарегистрироваться с уникальным именем и любым паролем • Авторизоваться
Авторизованный пользователь должен иметь возможность:
• Посмотреть инструкцию как оформить займ
• Оформить займ, ознакомившись с текущими условиями и указав необходимые данные
• Увидеть информацию о том, что займ успешно оформлен
• Видеть историю оформленных займов с актуальным статусом займа
• Обновлять историю оформленных займов
• При выборе займа в истории видеть детальную информацию о нём, а также инструкцию как его
получить в банке / оформить на карту
• Видеть необходимую информацию о произошедших ошибках, не нужно показывать юзеру текст
из Exception
• Не вводить заново логин и пароль при повторном открытии приложения

API
Ссылка на API: https://shiftlab.cft.ru:7777/

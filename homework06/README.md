<h3> Домашнее задание 06</h3>

<div class="learning-near__item">
<div class="text text_p-small text_default learning-markdown js-learning-markdown"><p>Переписать приложение для хранения книг на ORM</p>
</div>
<div class="text text_p-small text_default text_bold">Цель:</div>
<div class="text text_p-small text_default learning-markdown js-learning-markdown"><p>Цель: полноценно работать с JPA + Hibernate для подключения к реляционным БД посредством ORM-фреймворка<br>Результат: Высокоуровневое приложение с JPA-маппингом сущностей</p>
</div>
<br>
<div class="text text_p-small text_default text_bold">Описание/Пошаговая инструкция выполнения домашнего задания:</div>
<div class="text text_p-small text_default learning-markdown js-learning-markdown"><p>Домашнее задание выполняется переписыванием предыдущего на JPA.<br>Требования:</p>
<ol>
<li>Использовать JPA, Hibernate только в качестве JPA-провайдера.</li>
<li>Для решения проблемы N+1 можно использовать специфические для Hibernate аннотации @Fetch и @BatchSize.</li>
<li>Добавить сущность "комментария к книге", реализовать CRUD для новой сущности.</li>
<li>Покрыть репозитории тестами, используя H2 базу данных и соответствующий H2 Hibernate-диалект для тестов.</li>
<li>Не забудьте отключить DDL через Hibernate</li>
<li>@Transactional рекомендуется ставить только на методы сервиса.<br>Это домашнее задание будет использоваться в качестве основы для других ДЗ<br>Данная работа не засчитывает предыдущую!</li>
</ol>
</div>
</div>
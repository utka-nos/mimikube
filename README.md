#### <h>Запуск приложения:</h>
1. Запустить `minikube start`
2. Запустить агента на windows для дженкинса
3. Запустить Dashboard minikube
4. Запустить джобу деплоя в дженкинсе. Она создаст или обновит все необходимые поды
5. убедиться, что ingress addons is enabled. Если нет, то запустить командой `minikube addons enable ingress`
6. Найти сервис `ingress-nginx-controller` в неймспейсе `ingress-nginx` и пробросить от него url командой: 
`minikube service ingress-nginx-controller -n ingress-nginx --url`
7. Убедиться, что в файле C:\Windows\System32\drivers\etc\hosts указан хост, котторый мы роутим в ingress.yml
(В нашем случае это test.info для 127.0.0.1)
8. Если все сделано правильно, то после пункта 6. появится url, по которому можно будет достучаться до nginx контроллера
От него нам нужен только номер порта (один для http, другой для https)
Далее в браузере или где-либо еще идем по адресу http://test.info:*port*/ и получаем
наш сервис. *port* - тот порт, который мы возьмем из 6. пункта


#### <h>Второй вариант запуска с истио:</h>
1. Установлен istio и утилита командной строки istioctl
2. В kubernetes создан неймспейс istio-system. 
3. В этом неймспейсе есть деплойменты istio-egressgateway, istio-ingressgateway, istiod
4. И сервисы: istio-egressgateway (ClusterIP), istio-ingressgateway  (LoadBalancer), istiod (ClusterIP)
5. Если все необходимые компоненты установены, то, чтобы заработал сервис istio-ingressgateway, нужно включить 
туннелирование minikube tunnel
6. далее запускаем джобу из Jenkinsfile - она установит в кубер наше приложение
7. В /etc/hosts должны быть хосты test.info, prometheus.test.info, prometheus.prom который указывают на 127.0.0.1
8. Проверяем следующие эндпоинты:
 - http://test.info/ - начальная страница user-service
 - http://test.info:31400/ - адрес метрик с user-service
 - http://prometheus.prom:31400/ - ui прометеуса
 - https://test.info/ - начальная страница user-service (Сначала предупредить о том, что серитфикат ненадежный, он же наш)
 
 Чтобы получить доступ к https://prometheus.test.info:8083/ нам надо будет отправить с запросом наш клиентский сертификат с ключом.
Это получится сделать только из Postman. Для этого в настройках программы нужно будет во вкладке сертификатов
указать рутовый сертификат, а так же клиентский сертификат (.crt) и ключ (.key) для хоста prometheus.test.info:8083. 
Тогда при GET запросы нам вернется html страница прометеуса. Из браузера на этой странице мы лишь получим ERR_BAD_SSL_CLIENT_AUTH_CERT 
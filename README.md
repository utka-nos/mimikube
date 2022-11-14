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
Далее в браузере или где-либо еще идем по адресу http://test.info:<port>/ и получаем
наш сервис. <port> - тот порт, который мы возьмем из 6. пункта
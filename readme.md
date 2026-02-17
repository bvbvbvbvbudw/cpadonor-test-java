# CPA DONOR – Java test project (API / UI / Performance)

Це проект для тестування сайту: **API**, **UI** та **performance** (Gatling).

## Швидкий старт

### 1) Запуск API тестів
```bash
mvn -q -DsuiteXmlFile=testng-api.xml test
```

### 2) Запуск UI тестів
```bash
mvn -q -DsuiteXmlFile=testng-ui.xml test
```

Параметри (опційно):
```bash
mvn -q -DsuiteXmlFile=testng-ui.xml -DbaseUrl=https://cpadonor.com/en -DuserEmail=... -DuserPass=... test
```

### 3) Performance тести (Gatling) + параметризація

У проекті є 3 сценарії (симуляція `CpaDonorApiSimulation`):
1. `API: register click`
2. `API: get flow data`
3. `API: user journey` (click → коротка пауза → flow-data)

Запуск для різних наборів параметрів:

**1 user**
```bash
mvn -q gatling:test -Dgatling.simulationClass=com.cpadonor.perf.CpaDonorApiSimulation -Dusers=1 -DrampSeconds=10
```

**10 users**
```bash
mvn -q gatling:test -Dgatling.simulationClass=com.cpadonor.perf.CpaDonorApiSimulation -Dusers=10 -DrampSeconds=10
```

**30 users**
```bash
mvn -q gatling:test -Dgatling.simulationClass=com.cpadonor.perf.CpaDonorApiSimulation -Dusers=30 -DrampSeconds=10
```

Gatling HTML-репорти зберігаються тут:
`target/gatling/` (кожен запуск створює окрему папку з timestamp).

Параметри симуляції:
- `-Dusers=...` (кількість користувачів)
- `-DrampSeconds=...` (час розгону)
- `-DflowHash=...` (hash для flow-data)
- `-DapiBaseUrl=...` (base URL API)

## Allure

- Для **API**: використовується `AllureRestAssured()` — request/response автоматично лягають в Allure attachments.
- Для **UI**: `TestListener` додає attachments (скріншот + URL + page source) **і на success, і на failure** (щоб на дашборді було видно вкладення).

Локально:
```bash
mvn -q -DsuiteXmlFile=testng-api.xml test
mvn -q allure:report
```

Allure results: `target/allure-results`

## CI/CD

У репозиторії є workflow з 3 job:
- `api-tests`
- `ui-tests`
- `perf-tests`

Кожен job:
- запускає тести,
- зберігає `allure-results` і (для perf) `gatling report` як артефакти.

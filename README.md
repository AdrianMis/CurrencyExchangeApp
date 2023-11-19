# Aplikacja Obsługi Konta Walutowego

Celem zadania jest przygotowanie aplikacji serwującej API REST, która pozwoli na założenie konta oraz wymianę waluty w parze PLN<->USD.

## Założenia Funkcjonalne

### 1. Zakładanie Konta Walutowego

- Aplikacja udostępnia REST API pozwalające na zakładanie konta walutowego.
- Podczas zakładania konta wymagane jest podanie imienia, nazwiska oraz początkowego salda konta w PLN.
- Aplikacja generuje unikalny identyfikator konta, który powinien być używany do wywoływania dalszych metod API.

#### Przykład żądania:

```http
POST /api/accounts

{
  "firstName": "Jan",
  "lastName": "Kowalski",
  "initialBalancePLN": 1000.00
}
```

#### Przykładowa odpowiedź:

```json
{
  "accountId": 1,
  "firstName": "Jan",
  "lastName": "Kowalski",
  "balancePLN": 1000.00,
  "balanceUSD": 0.00
}
```

### 2. Wymiana Waluty

- Aplikacja umożliwia wymianę pieniędzy w parze PLN<->USD oraz USD<->PLN.
- Aktualny kurs wymiany pobierany jest z publicznego API NBP (http://api.nbp.pl/).

#### Przykład wymiany PLN na USD:

```http
POST /api/accounts/exchange

{
  "accountId": 1,
  "amount": 5.75,
  "currencyTo": "USD"
}
```

#### Przykładowa odpowiedź:

```json
{
    "id": 1,
    "firstName": "Jan",
    "lastName": "Kowalski",
    "balancePLN": 988.50,
    "balanceUSD": 2.83621050
}
```

### 3. Pobieranie Informacji o Koncie

- Aplikacja udostępnia REST API do pobierania danych o koncie oraz jego aktualnego stanu w PLN i USD.

#### Przykład żądania:

```http
GET /api/accounts/1
```

#### Przykładowa odpowiedź:

```json
{
    "id": 1,
    "firstName": "Jan",
    "lastName": "Kowalski",
    "balancePLN": 988.50,
    "balanceUSD": 2.83621050
}
```

## Dodatkowe Informacje

Dodano kolekcję Postmana (plik `postman_collection.json`), która zawiera przykładowe zapytania do testowania API.

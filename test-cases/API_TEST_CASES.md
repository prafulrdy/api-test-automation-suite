# API Test Cases
| ID | Endpoint | Method | Expected |
|---|---|---|---|
| API-001 | /products/1 | GET | 200 + product payload |
| API-002 | /products/add | POST | Success + generated id |
| API-003 | /products/1 | PUT | Updated payload |
| API-004 | /products/1 | DELETE | isDeleted=true |
| API-005 | /products/999999 | GET | 4xx |
| API-006 | /auth/login | POST | Access token returned |

# 제목 없음

# API 명세서

- 목록

| API | Method | URL |
| --- | --- | --- |
| 커피 메뉴 목록 조회 | GET | /api/menus |
| 포인트 충전 | POST | /api/chargepoints |
| 커피 주문/결제 | POST | /api/orders |
| 인기 메뉴 목록 조회 | GET | /api/rankings |

## 1. 커피 메뉴 목록 조회 API

### 설명

- 커피 메뉴 목록 조회하는 API

---

## 1. Request

### Request Headers

| Key | Value | 필수 여부 | 설명 |
| --- | --- | --- | --- |
| Authorization | Bearer {accessToken} | ✅ | JWT 인증 토큰 |

### PathVariable

없음

### Query Parameter

| 파라미터명 | 타입 | 필수 여부 | 기본값 | 설명 |
| --- | --- | --- | --- | --- |
| 없음 | 없음 | 없음 | 없음 | 없음 |

### Request Body

없음

### Request Example

```
GET /api/menus
Authorization: Bearer {accessToken}
```

---

## 2. Response

### 200 OK

```json
{
  "code": "SUCCESS",
  "data": {
    "coffeeMenu": [
      {
	      "menuId" : 1,
        "menuname": "아이스 아메리카노",
        "price": 2000,
        "description" : "좋은 원두를 쓴 아메리카노"
      }
    ]
  }
}
```

### Response Field

| 필드명 | 타입 | 설명 |
| --- | --- | --- |
| menuId | Long | 메뉴 고유 ID |
| menuname | String | 메뉴 이름 |
| price | int | 메뉴 가격 |
| description | String | 메뉴 설명 |

## 2. 포인트 충전 API

### 설명

- 결제 포인트 충전하는 API

---

## 1. Request

### Request Headers

| Key | Value | 필수 여부 | 설명 |
| --- | --- | --- | --- |
| Authorization | Bearer {accessToken} | ✅ | JWT 인증 토큰 |
|  |  |  |  |

### PathVariable

없음

### Query Parameter

| 파라미터명 | 타입 | 필수 여부 | 기본값 | 설명 |
| --- | --- | --- | --- | --- |
| 없음 | 없음 | 없음 | 없음 | 없음 |

### Request Body

```
{
	"userId" : 1,
	"price" : 10000
}
```

| 필드명 | 타입 | 설명 |
| --- | --- | --- |
| userId | Long | 사용자 고유 ID |
| price | int | 충전 금액 |

### Request Example

```
POST /api/chargepoints
Authorization: Bearer {accessToken}
```

---

## 2. Response

### 200 OK

```json
{
  "{userId}에 10000 포인트를 충전했습니다."
}
```

### Response Field

| 필드명 | 타입 | 설명 |
| --- | --- | --- |
| 없음 | 없음 | 없음 |

## 3. 커피 주문/결제하기 API

- 커피 주문 및 결제 요청하는 API

---

## 1. Request

### Request Headers

| Key | Value | 필수 여부 | 설명 |
| --- | --- | --- | --- |
| Authorization | Bearer {accessToken} | ✅ | JWT 인증 토큰 |

### PathVariable

없음

### Query Parameter

| 파라미터명 | 타입 | 필수 여부 | 기본값 | 설명 |
| --- | --- | --- | --- | --- |
| 없음 | 없음 | 없음 | 없음 | 없음 |

### Request Body

```
{
	"userId" : 1,
	"menuId" : 1,
	"price" : 10000
}
```

| 필드명 | 타입 | 설명 |
| --- | --- | --- |
| userId | Long | 사용자 고유 ID |
| menuId | Long | 메뉴 고유 ID |
| price | int | 충전 금액 |

### Request Example

```
POST /api/orders
Authorization: Bearer {accessToken}
```

---

## 2. Response

### 200 OK

```json
{
  "orderId" : 1,
  "orderNumber" : "ORDER-123",
  "totalAmount" : 2000,
  "usePoint" : 2000,
  "balance" : 8000
}
```

### Response Field

| 필드명 | 타입 | 설명 |
| --- | --- | --- |
| orderId | Long | 주문 고유 ID |
| orderNumber | String | 주문 번호 |
| totalAmount | int | 총 가격 |
| usePoint | int | 사용 포인트 |
| balance | int | 잔여 포인트 |

## 4. 인기 메뉴 목록 조회 API

- 7일간 인기있는 메뉴 3개 조회하는 API

---

## 1. Request

### Request Headers

| Key | Value | 필수 여부 | 설명 |
| --- | --- | --- | --- |
| Authorization | Bearer {accessToken} | ✅ | JWT 인증 토큰 |

### PathVariable

없음

### Query Parameter

| 파라미터명 | 타입 | 필수 여부 | 기본값 | 설명 |
| --- | --- | --- | --- | --- |
| 없음 | 없음 | 없음 | 없음 | 없음 |

### Request Body

```
없음
```

| 필드명 | 타입 | 설명 |
| --- | --- | --- |
| 없음 | 없음 | 없음 |

### Request Example

```
GET /api/ranking
Authorization: Bearer {accessToken}
```

---

## 2. Response

### 200 OK

```json
[
	{
	  "menuId" : 1,
	  "meunname" : "아이스 아메리카노",
	  "score" : 100.0
	},
	{
  "menuId" : 2,
  "meunname" : "아이스 바닐라 라떼",
  "score" : 80.0
	},
	{
  "menuId" : 3,
  "meunname" : "아이스 콜드브루",
  "score" : 60.0
	}
]	
```

### Response Field

| 필드명 | 타입 | 설명 |
| --- | --- | --- |
| menuId | Long | 메뉴 고유 ID |
| menuname | String | 메뉴 이름 |
| score | int | 메뉴 가격 |
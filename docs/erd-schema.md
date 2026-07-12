classDiagram
direction BT
class AbstractAuditable {
Instant  createdDate
Instant  lastModifiedDate
}
class AbstractPersistable {
PK  id
}
class BaseTimeEntity {
LocalDateTime  createdAt
LocalDateTime  updatedAt
}
class CartItem {
Long  id
Integer  quantity
}
class ChangelogMapping {
long  id
long  timestamp
}
class DefaultChangelog
class DefaultTrackingModifiedEntitiesChangelog
class Menu {
Long  id
String  description
String  name
Integer  price
Integer  stock
}
class Order {
Long  id
String  orderNumber
Integer  totalPrice
}
class Payment {
Long  id
LocalDateTime  paidAt
PaymentStatus  paymentStatus
Integer  totalPrice
Integer  usePoint
}
class PointHistory {
Long  id
Integer  amount
PointType  pointType
}
class TrackingModifiedEntitiesChangelogMapping {
Set~String~  modifiedEntityNames
}
class User {
Long  id
Integer  balance
String  email
String  name
String  password
}

AbstractAuditable  --|>  AbstractPersistable
CartItem  --|>  BaseTimeEntity
CartItem "0..*" --> "0..1" Menu
CartItem "0..*" --> "0..1" User
DefaultChangelog  --|>  ChangelogMapping
DefaultTrackingModifiedEntitiesChangelog  --|>  TrackingModifiedEntitiesChangelogMapping
Order  --|>  BaseTimeEntity
Order "0..*" --> "1" Menu
Order "0..*" --> "1" User
Payment  --|>  BaseTimeEntity
Payment "0..*" --> "1" Order
PointHistory  --|>  BaseTimeEntity
PointHistory "0..*" --> "0..1" User
TrackingModifiedEntitiesChangelogMapping  --|>  ChangelogMapping 

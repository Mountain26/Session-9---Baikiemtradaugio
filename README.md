# Quan ly kho hang Console (Java)

Ung dung quan ly san pham in-memory cho cong ty ban le voi:
- `entity.Product` abstract
- `entity.PhysicalProduct` (co `weight`)
- `entity.DigitalProduct` (co `sizeMb`)
- `database.ProductDatabase` dung mau Singleton
- `factory.ProductFactory` tao doi tuong theo loai
- Menu CRUD trong `Main`

## Chay nhanh

```powershell
javac src\*.java
java -cp src Main
```

## Chuc nang

1. Them moi san pham
2. Xem danh sach san pham
3. Cap nhat thong tin san pham
4. Xoa san pham
5. Thoat


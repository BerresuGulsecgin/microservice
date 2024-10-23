## MongoDB İşlemleri

Not : Mongo ile işlem yaparken admin kulllanıcısı ve admin şifresi kullanılmalıdır
    Bu nedenle oluşturulacak her bir db için yeni bir kullanıcı ve şifre tanımlanmalıdır.

1- Öncelikle DB oluşturun (UserProfileDb)
2- Üzerinde çalışma yapabilmek için MongoDB Compass üzerinden MONGOSH açın(sol altta)
3- "use databasename" şeklinde komut girilir
4- Bu DB yi yönetecek olan bir kullanıcı tanımlamalıyız 

db.createUser(
{
user:"defaultUser",
pwd:"bilge!123",
roles: ["readWrite","dbAdmin"]
}
)

db.createUser({user:"defaultUser",pwd:"bilge!123",roles: ["readWrite","dbAdmin"]})
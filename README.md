rmi
===

Remote Method Invocation Example

Uygulama:
Uygulama client ve server olmak üzere 2 bölümden oluşmaktadır. Client tarafında
kullanıcıya sunulan gui ile kullanıcı www.imdb.com üzerinden istediği film araması için gerekli
anahtar kelimeleri girmektedir. Kullanıcı aramayı başlattığında client uygulama server uygulamaya
gerekli parametreleri geçirmekte, server uygulamada bu parametrelerle sorgulama, html parse gibi
işlemeleri gerçekleştirerek client uygulamaya sonucu döndürmektedir. Dönen sonuç yine gui
içerisinde kullanıcıya sunulmaktadır. Film aramasının yanı sıra gui içerisinde 3 sn'de bir
güncellenen bir alanda www.foreca.com'dan alınan İzmir için hava durumu bilgisi
görüntülenmektedir.

Client:
RmiApp.java: Netbeans tarafından render edilen bir class'tır. Bir desktop (gui) application'ı
başlatmaya yarayan fonksiyonları içerir.

RmiInterface.java: Server'da override ettiğimiz fonksiyonları içeren Interface class'ı.

RmiView.java: Gui uygulamamızın kodlarını içeren class. Ayrıca server'a istekte
bulunduğumuz (parametre gönderme vs.) fonksiyonları da içeriyor.

UpdateWeather.java: Weather bilgisini aldığımız ve gui'yi güncellediğimiz Thread class'ı.
Server:
RmiInterface.java: Server'da override ettiğimiz fonksiyonları içeren Interface class'ı.

GetInfo.java: Server üzerinde yaptığımız işlemleri (http post, get, parse vb.) ayrı bir class
olarak burada gerçekleştirdik. RmiServer.java içerisinden bu fonksiyonları çağırıyoruz.

Uygulamanın çalıştırılması:
Server dosyalarının bulunduğu klasörün içinden rmiregistry çalıştırıldıktan sonra java
RmiServer komutu ile server uygulaması başlatılır. (Not: Server uygulaması localhost'ta çalışacak
şekilde şu anda başka bir bilgisayarda çalıştırılacaksa o bilgisayarın ip'si RmiServer içerisine
girildikten sonra RmiServer.java dosyası tekrar derlenip, stub tekrar oluşturulduktan sonra server
başlatılmalıdır.)

Server uygulaması başlatıldıktan sonra bin/client içerisinde client uygulamasının .jar dosyası
olarak paketlenmiş java -jar rmi.jar komutu ile client uygulaması başlatılır. Daha sonra gui'de
gerekli alana server uygulamasının çalıştığı bilgisayarın ip'si girilerek bağlantı sağlanır.

Stub dosyasını oluşturma:
server klasörü içerisinde bulunan RmiServer dosyası derlenerek .class dosyası oluşturulur.
> javac RmiServer.java

Daha sonra oluşturulan derlenmiş dosya Rmi Stub Compiler ile tekrar derlenerek stub dosyası oluşturulur.
> rmic RmiServer.class

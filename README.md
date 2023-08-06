# 한 번 더 작성하면서 주의할 점

1. 템플릿 엔진을 mustache 사용할 때, templates에 작성할 것
2. JPA를 사용하는 SpringBoot는 모델, DTO에 기본생성자를 꼭 넣을 것
3. model에 식별자(@Id, @GeneratedValue(strategy = GenerationType.IDENTITY)) 꼭 넣기
4. save 할 때, PK-FK인 sellerId는 @RequestParam으로 불러와서, 유효성(존재유무)검사 후 save 진행
5. 직렬화, 역직렬화라고.. 오류가 뜰 때, import 한 라이브러리 출저 확인할 것. Timestamp를 잘못 들고 와서 생긴 오류였다.
```java
java.io.StreamCorruptedException: invalid stream header: 32303233
```
6. update 등도 마찬가지였다. 꼭 try-catch로 하고, Null 오류가 발생한다면 Repository에서 select 하는데 데이터가 없을 떄 "null"이라고 return 받을 수 있도록 한다.
   * 참고 : SellerRepository - findByEmail
7. mustache는 날짜 포맷 기능이 없다.
8. 템플릿엔진에서 value는 데이터가 있는 곳, name은 Repository에서 name에 지정해놓은 이름이다.
```html
<input type="text" class="form-control" value={{product.seller.id}} name="sellerId">
```
9. 계속 id를 query.setParameter에서 빼 먹어서 곤란했는데, :데이터 받는 쿼리에서는 무조건 value 까먹지 말기
10. save 시 조건으로 select 할 때, null값이면 save한다고 가정하자. 이 때, 꼭 객체를 생성해서 select 값을 null과 비교하고 save할 수 있도록 하자. 객체 생성을 안 하니까 계속 null이라고만 뜨더라. Seller seller를 매개변수로 해 놓았기 때문에, seller = sellerRepository.findByEmail 안 먹혀...
```java
    @PostMapping("/seller/save")
    public String sellerSave(Seller seller) {
        Seller seller1 = sellerRepository.findByEmail(seller.getEmail());
        // 계속 안되었던 이유는... Seller seller1를 안해서...
        if (seller1 == null) {
            sellerRepository.save(seller);
            return "redirect:/seller";
        } else {
            return "redirect:/exist";
        }
    }
```
11. 더미데이터 만들 때, PK 테이블을 먼저 INSERT 할 것
12. redirect할 때, URL 엔드포인트를 작성할 것
13. 상품을 등록하는 save 같은 경우는, save는 아직 데이터가 생성 전이기 때문에 {id}와 같은 것이 필요없다. 템플릿 엔진에도 value값은 ""으로 해 놓을 것
   
## mustache 시 주의할 점
- application.yml에서 utf-8 체크하기
- templates에 mustache 파일넣기(WEB-INF/views는 추적이 힘들다...)

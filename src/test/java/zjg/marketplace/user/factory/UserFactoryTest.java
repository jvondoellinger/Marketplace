package zjg.marketplace.user.factory;

import zjg.marketplace.application.dto.user.UserInput;
import zjg.marketplace.core.user.entity.User;
import zjg.marketplace.core.user.entity.UserBuilder;
import zjg.marketplace.core.user.valueObj.cpf.CPF;
import zjg.marketplace.core.user.valueObj.phone.PhoneNumber;

import java.util.Date;


public class UserFactoryTest {


      public static UserInput factoryInput() {
            var input = new UserInput();
            var fakeDocument = new CPF();
            var date = new Date();
            var phoneNumber = new PhoneNumber();
            fakeDocument.setCpf("63033923429");
            date.setYear(date.getYear() - 20);

            phoneNumber.setNumber("000000000");
            phoneNumber.setAreaCode("21");
            phoneNumber.setCountryCode("55");

            input.setUsername("System");
            input.setDocument(fakeDocument);
            input.setEmail("system@fake.com");
            input.setPassword("AnyPassword123@@");

            input.setBirthDay(date);
            input.setPhone(phoneNumber);
            return input;
      }

      public static User factoryEntity() {
            var input = factoryInput();
            return UserBuilder.builder()
                    .document(input.getDocument())
                    .birthDay(input.getBirthDay())
                    .email(input.getEmail())
                    .username(input.getUsername())
                    .phone(input.getPhone())
                    .build();
      }
}

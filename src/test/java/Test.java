import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class Test {

    @org.junit.Test
    public void RegistrationWithValidValues(){
        open("http://users.bugred.ru/");
        $("a[href*=\"login\"]").click();
        $(By.name("name")).setValue("6");
        $(By.name("email")).setValue("6@gmail.com");
        $("div.col-md-6:nth-child(2) > form:nth-child(4) > " +
                "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(3) > " +
                "td:nth-child(2) > input:nth-child(1)").setValue("6");
        $(By.name("act_register_now")).click();
        $(".pull-right").shouldBe(Condition.visible);
    }

    @org.junit.Test
    public void LoginWithValidValues(){
        open("http://users.bugred.ru/");
        $("a[href*=\"login\"]").click();
        $(By.name("login")).setValue("6@gmail.com");
        $("div.col-md-6:nth-child(1) > form:nth-child(3) > " +
                "table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > " +
                "td:nth-child(2) > input:nth-child(1)").setValue("6");
        $("input[value= \"Авторизоваться\"]").click();
        $(".pull-right").shouldBe(Condition.visible);
    }

    @org.junit.Test
    public void CreateNewTask(){
        LoginWithValidValues();
        $("a[href=\"/tasks/index.html\"]").click();
        $(".btn-warning").click();
        sleep(2000);
        $(By.name("name")).setValue("test");
        $("textarea[name = \"description\"]").setValue("test");
        $(".btn-submit").click();
        $(".alert").shouldBe(Condition.visible);
    }

    @org.junit.Test
    public void EditTask(){
        LoginWithValidValues();
        $("a[href=\"/tasks/index.html\"]").click();
        $("a[href*=\"edit\"]").click();
        sleep(2000);
        $(By.name("name")).setValue("test test");
        $("textarea[name = \"description\"]").setValue("test test");
        $(".btn-submit").click();
        $(".alert").shouldBe(Condition.visible);
    }

    @org.junit.Test
    public void AddScheduleToTaskWithValidValues(){
        LoginWithValidValues();
        $("a[href=\"/tasks/index.html\"]").click();
        $(".btn-success").click();
        $(By.name("hours")).setValue("1");
        $(By.name("minuts")).setValue("59");
        $(".btn-submit").click();
        $(".alert").shouldBe(Condition.visible);
    }

    @org.junit.Test
    public void AddScheduleToTaskWithInvalidValues(){
        LoginWithValidValues();
        $("a[href=\"/tasks/index.html\"]").click();
        $(".btn-success").click();
        $(By.name("hours")).setValue("foo");
        $(By.name("minuts")).setValue("bar");
        $(".btn-submit").click();
        $(".alert").shouldBe(Condition.visible);
    }

    @org.junit.Test
    public void DeleteTask(){
        LoginWithValidValues();
        $("a[href=\"/tasks/index.html\"]").click();
        $("a[href*=\"delete\"]").click();
        sleep(2000);
        $("a[href*=\"delete\"]").shouldBe(Condition.not(Condition.visible));
    }

    @org.junit.Test
    public void AddNewUser(){
        LoginWithValidValues();
        $(".btn-danger").click();
        sleep(2000);
        $(".field_name > .input-lg").setValue("Test1");
        $(".field_email > .input-lg").setValue("test1@gmail.com");
        $(".field_password > .overflow").setValue("test");
        $(".btn-danger").scrollTo().click();
        $(".btn-success").shouldBe(Condition.visible);
    }

    @org.junit.Test
    public void IndicateThatTheTaskIsCompleted(){
        LoginWithValidValues();
        CreateNewTask();
        $("a[href=\"/tasks/my/index.html\"]").click();
        $(".btn-success").click();
        $("a[href=\"/tasks/my/completes\"]").click();
        sleep(2000);
        $(".table > tbody:nth-child(2) > tr:nth-child(1)").shouldBe(Condition.visible);
    }

    @org.junit.Test
    public void IndicateThatTheTaskIsNotCompleted(){
        LoginWithValidValues();
        CreateNewTask();
        $("a[href=\"/tasks/my/index.html\"]").click();
        $(".btn-danger").click();
        $("a[href=\"/tasks/my/fails\"]").click();
        sleep(2000);
        $(".table > tbody:nth-child(2) > tr:nth-child(1)").shouldBe(Condition.visible);
    }
}

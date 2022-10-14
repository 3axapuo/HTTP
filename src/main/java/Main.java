import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    public static final String URL = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        // ЗАДАЧА №1, создайте метод в который добавьте и настройте класс CloseableHttpClient например с помощью builder
        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build()) {
            // ЗАДАЧА №1, добавьте объект запроса HttpGet request =
            // new HttpGet("https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats")
            HttpGet request = new HttpGet(URL);
            // ЗАДАЧА №1, и вызовите удаленный сервис CloseableHttpResponse response = httpClient.execute(request);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                {
                    List<FactsAboutCats> responceList = mapper.readValue(response.getEntity().getContent(), new TypeReference<>() {
                    });
                    // ЗАДАЧА №1, программа должна прочитать весь список и вернуть только те факты, у которых поле upvotes не равно null или нулю.
                    filterList(responceList, i -> i.getUpvotes() == 0); //
                    responceList.forEach(System.out::println);
                }

            } catch (IOException e) {
                throw new RuntimeException();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Общий метод для удаления элементов из списка в Java
    public static <T> void filterList(List<T> list, Predicate<T> condition) {
        Set<T> toRemove = list.stream().filter(condition).collect(Collectors.toSet());
        list.removeAll(toRemove);
    }
}
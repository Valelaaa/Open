import com.example.openmind.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        // Получаем текущий токен из SessionManager
        val token = SessionManager.getJwtToken()

        // Создаем новый запрос с добавленным заголовком авторизации, если есть токен
        val newRequest = if (!token.isNullOrEmpty()) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        // Продолжаем выполнение цепочки запросов
        return chain.proceed(newRequest)
    }
}

import android.util.Log
import com.example.openmind.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

object AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val token = SessionManager.getJwtToken()
        Log.d("AuthInterceptor", "send request with token $token")
        val newRequest = if (token?.isNotBlank() == true) {
            originalRequest.newBuilder()
                .header("Authorization", "Bearer $token")
                .build()
        } else {
            originalRequest
        }

        return chain.proceed(newRequest)
    }
}

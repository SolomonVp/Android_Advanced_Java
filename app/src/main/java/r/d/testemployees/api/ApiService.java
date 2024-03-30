package r.d.testemployees.api;



import io.reactivex.Observable;
import r.d.testemployees.pojo.EmployeeResponse;
import retrofit2.http.GET;

public interface ApiService {
    @GET("testTask.json")
    Observable<EmployeeResponse> getEmployees();
}

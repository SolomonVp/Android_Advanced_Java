package r.d.testemployees.screens.employees;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import r.d.testemployees.R;
import r.d.testemployees.adapters.EmployeeAdapter;
import r.d.testemployees.api.ApiFactory;
import r.d.testemployees.api.ApiService;
import r.d.testemployees.pojo.Employee;
import r.d.testemployees.pojo.EmployeeResponse;
import r.d.testemployees.pojo.Speciality;

public class EmployeeListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewEmployees;
    private EmployeeAdapter adapter;
    private EmployeeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewEmployees = findViewById(R.id.recyclerViewEmployees);
        adapter = new EmployeeAdapter();
        adapter.setEmployees(new ArrayList<>());
        recyclerViewEmployees.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEmployees.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);
        viewModel.getEmployees().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(List<Employee> employees) {
                adapter.setEmployees(employees);
                if (employees != null) {
                    for (Employee employee : employees) {
                        List<Speciality> specialities = employee.getSpecialty();
                        for (Speciality speciality : specialities) {
                            Log.i("Speciality", speciality.getName());
                        }
                    }
                }

            }
        });
        viewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null) {
                    Toast.makeText(EmployeeListActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    viewModel.clearErrors();
                }
            }
        });
        viewModel.loadData();
    }
}
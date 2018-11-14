package pcs.ub.edu.ar.clinicavirtual.data;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

class AppointmentRepository implements IAppointmentsRepository{

    private final IMemoryAppointmentsDataSource mMemoryAppointmentsDataSource;
    private final ICloudAppointmentsDataSource mCloudAppointmentsDatasource;
    private final Context mContext;

    private boolean mReload;


    public AppointmentRepository(IMemoryAppointmentsDataSource memoryDataSource,
                                 ICloudAppointmentsDataSource cloudDataSource,
                                 Context context){
        mMemoryAppointmentsDataSource = checkNotNull(memoryDataSource);
        mCloudAppointmentsDatasource = checkNotNull(cloudDataSource);
        mContext = checkNotNull(context);
    }


    @Override
    public void getAppointments(GetAppointmentsCallback callback) {
        if(!mMemoryAppointmentsDataSource.mapIsNull() && !mReload){
                getAppointmentsFromMemory(callback);
                return;
        }

        if(mReload){
            getAppointmentsFromServer(callback, criteria);
        }else{
            List<Appointment> appointments = mMemoryAppointmentsDataSource.find();
            if(appointments.size() > 0){
                callback.onAppointmentsLoaded(appointments);
            }else{
                getAppointmentsFromServer(callback);
            }
        }
    }

    private void getAppointmentsFromMemory(GetAppointmentsCallback callback){
        callback.onAppointmentsLoaded(mMemoryAppointmentsDataSource.find());
    }

    private void getAppointmentsFromServer(final GetAppointmentsCallback callback){
        if(!isOnline()){
            callback.onDataNotAvailable("No hay conexion de red");
            return;
        }
        mCloudAppointmentsDatasource.getAppointments(
                new ICloudAppointmentsDataSource.AppointmentServiceCallback(){

                    @Override
                    public void onLoaded(List<Appointment> appointments) {
                        refreshMemoryDatasource(appointments);
                        getAppointmentsFromMemory(callback);
                    }

                    @Override
                    public void onError(String error) {
                        callback.onDataNotAvailable(error);
                    }
                });

    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return info != null && info.isConnectedOrConnecting();
    }

    private void refreshMemoryDatasource(List<Appointment> appointments){
        mMemoryAppointmentsDataSource.deleteAll();
        for (Appointment appointment: appointments){
            mMemoryAppointmentsDataSource.save(appointment);
        }
        mReload = false;
    }

    @Override
    public void refreshAppointments() {
        mReload = true;
    }
}

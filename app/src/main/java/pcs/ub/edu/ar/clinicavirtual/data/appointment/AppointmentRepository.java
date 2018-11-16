package pcs.ub.edu.ar.clinicavirtual.data.appointment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import pcs.ub.edu.ar.clinicavirtual.data.Appointment;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces.IAppointmentCriteria;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces.IAppointmentsRepository;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces.ICloudAppointmentsDataSource;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.interfaces.IMemoryAppointmentsDataSource;

class AppointmentRepository implements IAppointmentsRepository {

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
    public void getAppointments(GetAppointmentsCallback callback, final IAppointmentCriteria criteria) {

        if(!mMemoryAppointmentsDataSource.mapIsNull() && !mReload){
                getAppointmentsFromMemory(callback,criteria);
                return;
        }

        if(mReload){
            getAppointmentsFromServer(callback, criteria);
        }else{
            List<Appointment> appointments = mMemoryAppointmentsDataSource.find(criteria);
            if(appointments.size() > 0){
                callback.onAppointmentsLoaded(appointments);
            }else{
                getAppointmentsFromServer(callback,criteria);
            }
        }
    }

    private void getAppointmentsFromMemory(GetAppointmentsCallback callback, IAppointmentCriteria criteria){
        callback.onAppointmentsLoaded(mMemoryAppointmentsDataSource.find(criteria));
    }

    private void getAppointmentsFromServer(final GetAppointmentsCallback callback, final IAppointmentCriteria criteria){
        if(!isOnline()){
            callback.onDataNotAvailable("No hay conexion de red");
            return;
        }
        mCloudAppointmentsDatasource.getAppointments(
                new ICloudAppointmentsDataSource.AppointmentServiceCallback(){

                    @Override
                    public void onLoaded(List<Appointment> appointments) {
                        refreshMemoryDatasource(appointments);
                        getAppointmentsFromMemory(callback,criteria);
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

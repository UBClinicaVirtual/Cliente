package pcs.ub.edu.ar.clinicavirtual.data.appointment;

import android.content.Context;
import android.support.annotation.NonNull;

import pcs.ub.edu.ar.clinicavirtual.data.appointment.datasource.CloudAppointmentsDatasource;
import pcs.ub.edu.ar.clinicavirtual.data.appointment.datasource.MemoryAppointmentsDataSource;

import static com.google.common.base.Preconditions.checkNotNull;

public final class DependencyProvider {

    private static Context mContext;
    private static MemoryAppointmentsDataSource memorySource = null;
    private static CloudAppointmentsDatasource cloudSource = null;
    private static AppointmentRepository mAppointmentsRepository = null;

    private DependencyProvider() {
    }

    public static AppointmentRepository provideAppointmentsRepository(@NonNull Context context) {
        mContext = checkNotNull(context);
        if (mAppointmentsRepository == null) {
            mAppointmentsRepository = new AppointmentRepository(getMemorySource(),
                    getCloudSource(), context);
        }
        return mAppointmentsRepository;
    }

    public static MemoryAppointmentsDataSource getMemorySource() {
        if (memorySource == null) {
            memorySource = new MemoryAppointmentsDataSource();
        }
        return memorySource;
    }

    public static CloudAppointmentsDatasource getCloudSource() {
        if (cloudSource == null) {
            cloudSource = new CloudAppointmentsDatasource();
        }
        return cloudSource;
    }
}

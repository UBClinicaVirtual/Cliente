package pcs.ub.edu.ar.clinicavirtual.data;

import static com.google.common.base.Preconditions.checkNotNull;

public class AppointmentPresenter implements AppointmentMvp.Presenter {

    private final AppointmentRepository mAppointmentsRepository;
    private final AppointmentMvp.View mAppointmentsView;

    public static final int PRODUCTS_LIMIT = 20;

    private boolean isFirstLoad = true;
    private int mCurrentPage = 1;

    public AppointmentPresenter(AppointmentRepository appointmentsRepository,
                                AppointmentMvp.View appointmentsView){
        mAppointmentsRepository = checkNotNull(appointmentsRepository);
        mAppointmentsView = checkNotNull(appointmentsView);
    }

    @Override
    public void loadAppointment(final boolean reload) {
        final boolean reallyReload = reload || isFirstLoad;

        if(reallyReload){
            mAppointmentsView.showLoadingState(true);
            mAppointmentsRepository.refreshAppointments();
            mCurrentPage = 1;
        }else{
            mAppointmentsView.showLoadMoreIndicator(true);
            mCurrentPage++;
        }
        mAppointmentsRepository.getAppointments(..);
        }
    }


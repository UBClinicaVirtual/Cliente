package pcs.ub.edu.ar.clinicavirtual.data;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class AppointmentPresenter implements AppointmentMvp.Presenter {

    private final AppointmentRepository mAppointmentsRepository;
    private final AppointmentMvp.View mAppointmentsView;

    public static final int PRODUCTS_LIMIT = 1;

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

        IAppointmentCriteria criteria = new PagingAppointmentCriteria(mCurrentPage, PRODUCTS_LIMIT);
        mAppointmentsRepository.getAppointments(
                new AppointmentRepository.GetAppointmentsCallback(){

                    @Override
                    public void onAppointmentsLoaded(List<Appointment> appointments) {
                        mAppointmentsView.showLoadingState(false);
                        processAppointments (appointments, reallyReload);

                        isFirstLoad = false;
                    }

                    @Override
                    public void onDataNotAvailable(String error) {
                        mAppointmentsView.showLoadingState(false);
                        mAppointmentsView.showLoadMoreIndicator(false);
                        mAppointmentsView.showAppointmentsError(error);
                    }
                },criteria);
        }

        private void processAppointments(List<Appointment> appointments, boolean reload){
        if(appointments.isEmpty()){
            if(reload){
                mAppointmentsView.showEmptyState();
            }else{
                mAppointmentsView.showLoadMoreIndicator(false);
            }
            mAppointmentsView.allowMoreData(false);
        }else{
            if(reload){
                mAppointmentsView.showAppointments(appointments);
            }else{
                mAppointmentsView.showLoadMoreIndicator(false);
                mAppointmentsView.showAppointmentPage(appointments);
            }
            mAppointmentsView.allowMoreData(true);
        }
        }
    }


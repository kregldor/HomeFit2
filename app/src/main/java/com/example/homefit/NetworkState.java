package com.example.homefit;

import android.os.Build;

import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.List;
import java.util.Objects;

public abstract class NetworkState {
    private NetworkState() {

    }

    public abstract String getMessage();

    public abstract class Success extends NetworkState {
        @Override
        public String getMessage() {
            return "No error found";
        }
    }

    public static final class Error extends NetworkState {
        String error;

        public Error(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }


        @Override
        public String getMessage() {
            return error;
        }
    }

    public final class NetworkException extends NetworkState {
        String error;

        public NetworkException(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        @Override
        public String getMessage() {
            return error;
        }


    }

    public static abstract class HttpErrors extends NetworkState {
        HttpErrors() {

        }

        public static final class ResourceForbidden extends HttpErrors {
            String exception;

            public ResourceForbidden(String exception) {
                this.exception = exception;
            }

            public String getException() {
                return exception;
            }

            public void setException(String exception) {
                this.exception = exception;
            }

            @Override
            public String getMessage() {
                return exception;
            }

        }

        public static final class ResourceNotFound extends HttpErrors {
            String exception;

            public ResourceNotFound(String exception) {
                this.exception = exception;
            }

            public String getException() {
                return exception;
            }

            public void setException(String exception) {
                this.exception = exception;
            }

            @Override
            public String getMessage() {
                return exception;
            }

        }

        public static final class InternalServerError extends HttpErrors {
            String exception;

            public InternalServerError(String exception) {
                this.exception = exception;
            }

            public String getException() {
                return exception;
            }

            public void setException(String exception) {
                this.exception = exception;
            }

            @Override
            public String getMessage() {
                return exception;
            }

        }

        public static final class BadGateWay extends HttpErrors {
            String exception;

            public BadGateWay(String exception) {
                this.exception = exception;
            }

            public String getException() {
                return exception;
            }

            public void setException(String exception) {
                this.exception = exception;
            }

            @Override
            public String getMessage() {
                return exception;
            }

        }

        public static final class ResourceRemoved extends HttpErrors {
            String exception;

            public ResourceRemoved(String exception) {
                this.exception = exception;
            }

            public String getException() {
                return exception;
            }

            public void setException(String exception) {
                this.exception = exception;
            }

            @Override
            public String getMessage() {
                return exception;
            }

        }

        public static final class RemovedResourceFound extends HttpErrors {
            String exception;

            public RemovedResourceFound(String exception) {
                this.exception = exception;
            }

            public String getException() {
                return exception;
            }

            public void setException(String exception) {
                this.exception = exception;
            }

            @Override
            public String getMessage() {
                return exception;
            }

        }

    }


}

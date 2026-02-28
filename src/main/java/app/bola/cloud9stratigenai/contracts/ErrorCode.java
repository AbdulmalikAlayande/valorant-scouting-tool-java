package app.bola.cloud9stratigenai.contracts;

public enum ErrorCode {
    RETRYABLE_PROVIDER,
    RETRYABLE_INFRA,
    NON_RETRYABLE_DATA,
    NON_RETRYABLE_CONTRACT,
    NON_RETRYABLE_AUTH,
    NON_RETRYABLE_CONFIG,
    UNKNOWN
}

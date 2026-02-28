package app.bola.cloud9stratigenai.contracts;

public enum WorkflowState {
    QUEUED,
    INGESTING,
    FEATURIZING,
    SYNTHESIZING,
    COMPOSING,
    READY,
    FAILED,
    CANCELLED
}

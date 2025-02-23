import React from "react";
import PropTypes from "prop-types";

import ReadOnlyFormGroup from "./components/ReadOnlyFormGroup";

const PushNotificationDetails = ({ notification}) => {
    return (
        <>
        <ReadOnlyFormGroup label="User/Group Token" value={notification.config.user_token} />
        <ReadOnlyFormGroup label="API Token" value={notification.config.api_token} />
        <ReadOnlyFormGroup label="Message" value={notification.config.message_field} />
        <ReadOnlyFormGroup label="Priority" value={notification.config.priority_token} />
        <ReadOnlyFormGroup label="Retry" value={notification.config.retry_token} />
        <ReadOnlyFormGroup label="Expire" value={notification.config.expire_token} />
        <ReadOnlyFormGroup label="Sound" value={notification.config.sound_token} />
      </>
    );
};

PushNotificationDetails.PropTypes = {
    notification: PropTypes.object.isRequired,
};

export default PushNotificationDetails;

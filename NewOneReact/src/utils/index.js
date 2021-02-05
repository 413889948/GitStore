export const sleep = time => new Promise(resolve => setTimeout(resolve, time));

export const getContextPath = () => {
    const basePath = window.SHARE.CONTEXT_PATH;

    return basePath.replace(location.origin, '');
};

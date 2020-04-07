const basePath = (process.env.NODE_ENV === 'production' ? '/todo-list':'');

export const doRequest = (path, params) => {
	let mergedParams = {
	    credentials: 'same-origin',
	    ...params
	 }
	return fetch(basePath+path, mergedParams);
}

export const doRequestWithBody = (path, method, body) => {
	return doRequest(path, { 
	    headers: {
	      'content-type': 'application/json'
	    },
	    method, 
	    body: JSON.stringify(body)
	})
}

export const doDeleteRequest = (path) => {
	return doRequest(path, { 
	    method: 'delete'
	})
}
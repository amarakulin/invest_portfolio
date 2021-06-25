const createURLSearchParam = (data) => {
	let params = new URLSearchParams();

	for (let key in data)
		params.append(key, data[key]);
	return params;
}

export default createURLSearchParam;
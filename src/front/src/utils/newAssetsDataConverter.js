export const newAssetsDataConverter = (data) => {
	const arr = Object.values(data);
	const finalArr = [];

	for (let i = 0; i < arr.length; i += 2) {
		finalArr.push({
			ticker: arr[i],
			amount: arr[i + 1]
		})
	}

	return {
		value: finalArr
	}
}
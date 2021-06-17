export const newAssetsDataConverter = (data) => {
	const getIdArr = (data) => {
		let setId = new Set();

		for (let el in data) {
			setId.add(+el.slice(el.indexOf('-') + 1))
		}

		return (Array.from([...setId]));
	}

	let id = getIdArr(data);
	const finalArr = [];

	for (let i = 0; i < id.length; i++) {
		finalArr.push({
			ticker: data[`search-${id[i]}`],
			amount: data[`amount-${id[i]}`]
		})
	}

	return finalArr;
}
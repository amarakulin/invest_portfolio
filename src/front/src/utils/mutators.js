export const setValue = (args, state, utils) => {
	utils.changeValue(state, args[0], () => args[1])
}
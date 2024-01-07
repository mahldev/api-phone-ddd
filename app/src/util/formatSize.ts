const formatSize = (storage: number): string => {
  return storage >= 1000 ? `${storage / 1000}TB` : `${storage}GB`
}

export default formatSize
